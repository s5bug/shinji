package tf.bug.shinji

trait ClosedMonoidal[Obj, Hom[_ <: Obj, _ <: Obj], I <: Obj, Tens[_ <: Obj, _ <: Obj] <: Obj, Exp[_ <: Obj, _ <: Obj] <: Obj] extends Closed[Obj, Hom, I, Tens, Exp] with Monoidal[Obj, Hom, I, Tens] {
  def curry[A <: Obj, B <: Obj, C <: Obj](f: Hom[Tens[A, B], C]): Hom[A, Exp[B, C]]
  def uncurry[A <: Obj, B <: Obj, C <: Obj](f: Hom[A, Exp[B, C]]): Hom[Tens[A, B], C]
}
