package tf.bug.shinji

trait Monad[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  F[_ <: Obj] <: Obj,
] extends Endofunctor[Obj, Val, Hom, F] {

  def pure[A <: Obj](v: Val[A]): Hom[A, F[A]]

  def flatten[A <: Obj](v: Val[A]): Hom[F[F[A]], F[A]]

  def flatMap[A <: Obj, B <: Obj](v: Val[B], f: Hom[A, F[B]]): Hom[F[A], F[B]] =
    endofunctorCategory.compose(map(f), flatten(v))

}

object Monad {

  def identity[Obj, Val[_ <: Obj], Hom[_ <: Obj, _ <: Obj]](cat: Category[Obj, Val, Hom]): Monad[Obj, Val, Hom, λ[`α <: Obj` => α]] =
    new Monad[Obj, Val, Hom, λ[`α <: Obj` => α]] {
      override val endofunctorCategory: Category[Obj, Val, Hom] = cat

      override def mapVal[A <: Obj](v: Val[A]): Val[A] = v

      override def map[A <: Obj, B <: Obj](f: Hom[A, B]): Hom[A, B] = f

      override def pure[A <: Obj](v: Val[A]): Hom[A, A] = cat.id(v)

      override def flatten[A <: Obj](v: Val[A]): Hom[A, A] = cat.id(v)
    }

  def transform[
    ObjE, ValE[_ <: ObjE], HomE[_ <: ObjE, _ <: ObjE],
    ObjI, ValI[_ <: ObjI], HomI[_ <: ObjI, _ <: ObjI],
    U[_ <: ObjE] <: ObjI, F[_ <: ObjI] <: ObjE,
    M[_ <: ObjI] <: ObjI
  ](
    cat: Category[ObjE, ValE, HomE],
    adj: Adjunction[ObjE, ValE, HomE, ObjI, ValI, HomI, U, F],
    from: Monad[ObjI, ValI, HomI, M]
  ): Monad[ObjE, ValE, HomE, λ[`α <: ObjE` => F[M[U[α]]]]] =
    new Monad[ObjE, ValE, HomE, λ[`α <: ObjE` => F[M[U[α]]]]] {
      override val endofunctorCategory: Category[ObjE, ValE, HomE] = cat

      override def mapVal[A <: ObjE](v: ValE[A]): ValE[F[M[U[A]]]] = {
        val toU: ValI[U[A]] = adj.free.mapVal(v)
        val toM: ValI[M[U[A]]] = from.mapVal(toU)
        val toF: ValE[F[M[U[A]]]] = adj.forgetful.mapVal(toM)
        toF
      }

      override def map[A <: ObjE, B <: ObjE](f: HomE[A, B]): HomE[F[M[U[A]]], F[M[U[B]]]] = {
        val toU: HomI[U[A], U[B]] = adj.free.map(f)
        val toM: HomI[M[U[A]], M[U[B]]] = from.map(toU)
        val toF: HomE[F[M[U[A]]], F[M[U[B]]]] = adj.forgetful.map(toM)
        toF
      }

      override def pure[A <: ObjE](v: ValE[A]): HomE[A, F[M[U[A]]]] = {
        val uaV = adj.free.mapVal(v)
        val aToFu: HomE[A, F[U[A]]] = adj.iso[A, U[A]](v, uaV).forward(from.endofunctorCategory.id(uaV))
        val uToMu: HomI[U[A], M[U[A]]] = from.pure(uaV)
        val fuToFmu: HomE[F[U[A]], F[M[U[A]]]] = adj.forgetful.map(uToMu)
        cat.compose(aToFu, fuToFmu)
      }

      override def flatten[A <: ObjE](v: ValE[A]): HomE[F[M[U[F[M[U[A]]]]]], F[M[U[A]]]] = {
        val uaV = adj.free.mapVal(v)
        val muaV = from.mapVal(uaV)
        val fmuaV = adj.forgetful.mapVal(muaV)
        val ufmuToMu: HomI[U[F[M[U[A]]]], M[U[A]]] = adj.iso[F[M[U[A]]], M[U[A]]](fmuaV, muaV).backward(cat.id(fmuaV))
        val mufmuToMmu: HomI[M[U[F[M[U[A]]]]], M[M[U[A]]]] = from.map(ufmuToMu)
        val mufmuToMu: HomI[M[U[F[M[U[A]]]]], M[U[A]]] = from.endofunctorCategory.compose(mufmuToMmu, from.flatten(uaV))
        adj.forgetful.map(mufmuToMu)
      }
    }

}
