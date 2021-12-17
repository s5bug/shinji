package tf.bug.shinji

trait Profunctor[
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

  def dimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](l: HomC[C, A], r: HomD[B, D]): HomE[F[A, B], F[C, D]]

  def fixLeft[A <: ObjC](va: ValC[A]): Functor[ObjD, ValD, HomD, ObjE, ValE, HomE, λ[`α <: ObjD` => F[A, α]]] =
    new Functor[ObjD, ValD, HomD, ObjE, ValE, HomE, λ[`α <: ObjD` => F[A, α]]] {
      override val functorCategoryIn: Category[ObjD, ValD, HomD] = Profunctor.this.functorCategoryInRight
      override val functorCategoryOut: Category[ObjE, ValE, HomE] = Profunctor.this.functorCategoryOut

      override def mapVal[B <: ObjD](vb: ValD[B]): ValE[F[A, B]] =
        Profunctor.this.mapVals(va, vb)

      override def map[I <: ObjD, J <: ObjD](f: HomD[I, J]): HomE[F[A, I], F[A, J]] =
        Profunctor.this.dimap(Profunctor.this.functorCategoryInLeft.id(va), f)
    }

}
