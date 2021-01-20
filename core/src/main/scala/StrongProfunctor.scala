package tf.bug.shinji

trait StrongProfunctor[Obj, Hom[_ <: Obj, _ <: Obj], I <: Obj, Tens[_ <: Obj, _ <: Obj] <: Obj, F[_ <: Obj, _ <: Obj]] extends Profunctor[Obj, Hom, Obj, Hom, F] {

  val strongProfunctorCategory: Monoidal[Obj, Hom, I, Tens]
  override final val leftProfunctorCategory = strongProfunctorCategory
  override final val rightProfunctorCategory = strongProfunctorCategory

  def first[A <: Obj, B <: Obj, C <: Obj]: F[A, B] => F[Tens[A, C], Tens[B, C]]
  def second[A <: Obj, B <: Obj, C <: Obj]: F[A, B] => F[Tens[C, A], Tens[C, B]]

}
