package tf.bug.shinji

trait Monoid[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj,
  A <: Obj,
] {

  val monoidalCategory: MonoidalCategory[Obj, Val, Hom, I, Tens]

  def empty: Hom[I, A]
  def combine: Hom[Tens[A, A], A]

}
