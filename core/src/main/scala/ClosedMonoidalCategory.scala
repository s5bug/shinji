package tf.bug.shinji

trait ClosedMonoidalCategory[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj,
] extends MonoidalCategory[Obj, Val, Hom, I, Tens] with ClosedCategory[Obj, Val, Hom, I, Exp] {

  def curry[A <: Obj, B <: Obj, C <: Obj]: Iso[Any, λ[α => Unit], Function1, Hom[Tens[A, B], C], Hom[A, Exp[B, C]]]

}
