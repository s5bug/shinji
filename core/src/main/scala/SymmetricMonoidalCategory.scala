package tf.bug.shinji

trait SymmetricMonoidalCategory[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj,
] extends MonoidalCategory[Obj, Val, Hom, I, Tens] {

  def braid[X <: Obj, Y <: Obj]: Iso[Obj, Val, Hom, Tens[X, Y], Tens[Y, X]]

}
