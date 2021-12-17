package tf.bug.shinji

trait MonoidalCategory[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj,
] extends Category[Obj, Val, Hom] {

  val monoidalTensBifunctor: Bifunctor[Obj, Val, Hom, Obj, Val, Hom, Obj, Val, Hom, Tens]
  def tensExtractLeft[A <: Obj, B <: Obj](v: Val[Tens[A, B]]): Val[A]
  def tensExtractRight[A <: Obj, B <: Obj](v: Val[Tens[A, B]]): Val[B]

  def identity: I
  def identityVal: Val[I]

  def associative[A <: Obj, B <: Obj, C <: Obj]: Iso[Obj, Val, Hom, Tens[Tens[A, B], C], Tens[A, Tens[B, C]]]

  def leftUnit[A <: Obj]: Iso[Obj, Val, Hom, Tens[I, A], A]
  def rightUnit[A <: Obj]: Iso[Obj, Val, Hom, Tens[A, I], A]

}
