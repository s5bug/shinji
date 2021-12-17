package tf.bug.shinji

trait Bifunctor[
  ObjC,
  ValC[_ <: ObjC],
  HomC[_ <: ObjC, _ <: ObjC],
  ObjD,
  ValD[_ <: ObjD],
  HomD[_ <: ObjD, _ <: ObjD],
  ObjE,
  ValE[_ <: ObjE],
  HomE[_ <: ObjE, _ <: ObjE],
  F[_ <: ObjC, _ <: ObjD] <: ObjE,
] {

  val functorCategoryInLeft: Category[ObjC, ValC, HomC]
  val functorCategoryInRight: Category[ObjD, ValD, HomD]
  val functorCategoryOut: Category[ObjE, ValE, HomE]

  def mapVals[A <: ObjC, B <: ObjD](va: ValC[A], vb: ValD[B]): ValE[F[A, B]]

  def bimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](l: HomC[A, C], r: HomD[B, D]): HomE[F[A, B], F[C, D]]

  def fixLeft[A <: ObjC](va: ValC[A]): Functor[ObjD, ValD, HomD, ObjE, ValE, HomE, λ[`α <: ObjD` => F[A, α]]] =
    new Functor[ObjD, ValD, HomD, ObjE, ValE, HomE, λ[`α <: ObjD` => F[A, α]]] {
      override val functorCategoryIn: Category[ObjD, ValD, HomD] = Bifunctor.this.functorCategoryInRight
      override val functorCategoryOut: Category[ObjE, ValE, HomE] = Bifunctor.this.functorCategoryOut

      override def mapVal[B <: ObjD](vb: ValD[B]): ValE[F[A, B]] =
        Bifunctor.this.mapVals(va, vb)

      override def map[I <: ObjD, J <: ObjD](f: HomD[I, J]): HomE[F[A, I], F[A, J]] =
        Bifunctor.this.bimap(Bifunctor.this.functorCategoryInLeft.id(va), f)
    }

  def fixRight[B <: ObjD](vb: ValD[B]): Functor[ObjC, ValC, HomC, ObjE, ValE, HomE, λ[`α <: ObjC` => F[α, B]]] =
    new Functor[ObjC, ValC, HomC, ObjE, ValE, HomE, λ[`α <: ObjC` => F[α, B]]] {
      override val functorCategoryIn: Category[ObjC, ValC, HomC] = Bifunctor.this.functorCategoryInLeft
      override val functorCategoryOut: Category[ObjE, ValE, HomE] = Bifunctor.this.functorCategoryOut

      override def mapVal[A <: ObjC](va: ValC[A]): ValE[F[A, B]] =
        Bifunctor.this.mapVals(va, vb)

      override def map[I <: ObjC, J <: ObjC](f: HomC[I, J]): HomE[F[I, B], F[J, B]] =
        Bifunctor.this.bimap(f, Bifunctor.this.functorCategoryInRight.id(vb))
    }

}
