package tf.bug.shinji

trait SymmetricClosedMonoidalCategory[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj,
] extends ClosedMonoidalCategory[Obj, Val, Hom, I, Tens, Exp] with SymmetricMonoidalCategory[Obj, Val, Hom, I, Tens] {

  def homTensAdj[B <: Obj]: Adjunction[
    Obj, Val, Hom,
    Obj, Val, Hom,
    λ[`α <: Obj` => Tens[B, α]],
    λ[`α <: Obj` => Exp[B, α]],
  ]

  override def point[A <: Obj](v: Val[A]): Hom[A, Exp[I, A]] = {
    val lu: Hom[Tens[I, A], A] = leftUnit[A].forward
    homTensAdj[I].iso[A, A](v, v).forward(lu)
  }

  override def curry[A <: Obj, B <: Obj, C <: Obj]: Iso[Any, λ[α => Unit], Function, Hom[Tens[A, B], C], Hom[A, Exp[B, C]]] =
    Iso[Any, λ[α => Unit], Function, Hom[Tens[A, B], C], Hom[A, Exp[B, C]]](
      forward = (f: Hom[Tens[A, B], C]) => {
        val va: Val[A] = tensExtractLeft(homExtractLeft(f))
        val vb: Val[B] = tensExtractRight(homExtractLeft(f))
        val vc: Val[C] = homExtractRight(f)
        val flip: Hom[Tens[B, A], Tens[A, B]] = braid[A, B].backward
        homTensAdj[B].iso[A, C](va, vc).forward(compose(flip, f))
      },
      backward = (f: Hom[A, Exp[B, C]]) => {
        val va: Val[A] = homExtractLeft(f)
        val vb: Val[B] = expExtractLeft(homExtractRight(f))
        val vc: Val[C] = expExtractRight(homExtractRight(f))
        val flip: Hom[Tens[A, B], Tens[B, A]] = braid[A, B].forward
        compose(flip, homTensAdj[B].iso[A, C](va, vc).backward(f))
      }
    )

}
