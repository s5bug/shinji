package tf.bug.shinji

trait Closed[Obj, Hom[_ <: Obj, _ <: Obj], I <: Obj, Exp[_ <: Obj, _ <: Obj] <: Obj] extends Category[Obj, Hom] with Profunctor[Obj, Hom, Obj, Hom, Obj, Hom, Exp] {
  def expId[A <: Obj]: Hom[I, Exp[A, A]]
  def expCompose[A <: Obj, B <: Obj, C <: Obj]: Hom[Exp[A, B], Exp[Exp[C, A], Exp[C, B]]]

  override val leftCategory: Category[Obj, Hom] = this
  override val rightCategory: Category[Obj, Hom] = this
  override val outCategory: Category[Obj, Hom] = this
}
