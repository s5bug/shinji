package tf.bug.shinji

trait Adjunction[
  ObjE,
  ValE[_ <: ObjE],
  HomE[_ <: ObjE, _ <: ObjE],
  ObjI,
  ValI[_ <: ObjI],
  HomI[_ <: ObjI, _ <: ObjI],
  U[_ <: ObjE] <: ObjI,
  F[_ <: ObjI] <: ObjE,
] {

  def free: Functor[ObjE, ValE, HomE, ObjI, ValI, HomI, U]
  def forgetful: Functor[ObjI, ValI, HomI, ObjE, ValE, HomE, F]

  def iso[A <: ObjE, B <: ObjI](va: ValE[A], vb: ValI[B]): Iso[Any, λ[α => Unit], Function1, HomI[U[A], B], HomE[A, F[B]]]

}

object Adjunction {

  def compose[
    ObjE, ValE[_ <: ObjE], HomE[_ <: ObjE, _ <: ObjE],
    ObjM, ValM[_ <: ObjM], HomM[_ <: ObjM, _ <: ObjM],
    ObjI, ValI[_ <: ObjI], HomI[_ <: ObjI, _ <: ObjI],
    U[_ <: ObjE] <: ObjM, V[_ <: ObjM] <: ObjI,
    F[_ <: ObjM] <: ObjE, G[_ <: ObjI] <: ObjM,
  ](
    outer: Adjunction[ObjE, ValE, HomE, ObjM, ValM, HomM, U, F],
    inner: Adjunction[ObjM, ValM, HomM, ObjI, ValI, HomI, V, G]
  ): Adjunction[ObjE, ValE, HomE, ObjI, ValI, HomI, λ[`α <: ObjE` => V[U[α]]], λ[`α <: ObjI` => F[G[α]]]] =
    new Adjunction[ObjE, ValE, HomE, ObjI, ValI, HomI, λ[`α <: ObjE` => V[U[α]]], λ[`α <: ObjI` => F[G[α]]]] {
      override def free: Functor[ObjE, ValE, HomE, ObjI, ValI, HomI, Lambda[`α <: ObjE` => V[U[α]]]] =
        Functor.compose(outer.free, inner.free)

      override def forgetful: Functor[ObjI, ValI, HomI, ObjE, ValE, HomE, Lambda[`α <: ObjI` => F[G[α]]]] =
        Functor.compose(inner.forgetful, outer.forgetful)

      override def iso[A <: ObjE, B <: ObjI](va: ValE[A], vb: ValI[B]): Iso[Any, λ[α => Unit], Function1, HomI[V[U[A]], B], HomE[A, F[G[B]]]] =
        Iso[Any, λ[α => Unit], Function1, HomI[V[U[A]], B], HomE[A, F[G[B]]]](
          forward = (f: HomI[V[U[A]], B]) => {
            val transp: HomM[U[A], G[B]] = inner.iso(outer.free.mapVal(va), vb).forward(f)
            val again: HomE[A, F[G[B]]] = outer.iso(va, inner.forgetful.mapVal(vb)).forward(transp)
            again
          },
          backward = (f: HomE[A, F[G[B]]]) => {
            val transp: HomM[U[A], G[B]] = outer.iso(va, inner.forgetful.mapVal(vb)).backward(f)
            val again: HomI[V[U[A]], B] = inner.iso(outer.free.mapVal(va), vb).backward(transp)
            again
          }
        )
    }

}
