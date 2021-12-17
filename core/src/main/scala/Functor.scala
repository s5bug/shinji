package tf.bug.shinji

trait Functor[
  ObjC,
  ValC[_ <: ObjC],
  HomC[_ <: ObjC, _ <: ObjC],
  ObjD,
  ValD[_ <: ObjD],
  HomD[_ <: ObjD, _ <: ObjD],
  F[_ <: ObjC] <: ObjD,
] {

  val functorCategoryIn: Category[ObjC, ValC, HomC]
  val functorCategoryOut: Category[ObjD, ValD, HomD]

  def mapVal[A <: ObjC](v: ValC[A]): ValD[F[A]]

  def map[A <: ObjC, B <: ObjC](f: HomC[A, B]): HomD[F[A], F[B]]

}

object Functor {

  def compose[
    ObjC,
    ValC[_ <: ObjC],
    HomC[_ <: ObjC, _ <: ObjC],
    ObjD,
    ValD[_ <: ObjD],
    HomD[_ <: ObjD, _ <: ObjD],
    ObjE,
    ValE[_ <: ObjE],
    HomE[_ <: ObjE, _ <: ObjE],
    F[_ <: ObjC] <: ObjD,
    G[_ <: ObjD] <: ObjE,
  ](
    f: Functor[ObjC, ValC, HomC, ObjD, ValD, HomD, F],
    g: Functor[ObjD, ValD, HomD, ObjE, ValE, HomE, G],
  ): Functor[ObjC, ValC, HomC, ObjE, ValE, HomE, λ[`α <: ObjC` => G[F[α]]]] =
    new Functor[ObjC, ValC, HomC, ObjE, ValE, HomE, λ[`α <: ObjC` => G[F[α]]]] {
      override val functorCategoryIn: Category[ObjC, ValC, HomC] = f.functorCategoryIn
      override val functorCategoryOut: Category[ObjE, ValE, HomE] = g.functorCategoryOut

      override def mapVal[A <: ObjC](v: ValC[A]): ValE[G[F[A]]] = g.mapVal(f.mapVal(v))

      override def map[A <: ObjC, B <: ObjC](h: HomC[A, B]): HomE[G[F[A]], G[F[B]]] = g.map(f.map(h))
    }

}

trait Endofunctor[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  F[_ <: Obj] <: Obj
] extends Functor[Obj, Val, Hom, Obj, Val, Hom, F] {

  val endofunctorCategory: Category[Obj, Val, Hom]

  override final val functorCategoryIn = endofunctorCategory
  override final val functorCategoryOut = endofunctorCategory

}
