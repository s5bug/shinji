package tf.bug.shinji

trait StrongProfunctor[
  Obj,
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj,
  F[_ <: Obj, _ <: Obj]
] extends Profunctor[Obj, Con, Hom, Obj, Con, Hom, F] {

  def strongProfunctorCategory: Monoidal[Obj, Con, Hom, I, Tens]
  override final def leftProfunctorCategory: Monoidal[Obj, Con, Hom, I, Tens] = strongProfunctorCategory
  override final def rightProfunctorCategory: Monoidal[Obj, Con, Hom, I, Tens] = strongProfunctorCategory

  def first[A <: Obj, B <: Obj, C <: Obj](f: F[A, B])(implicit c: Con[C]): F[Tens[A, C], Tens[B, C]]
  def second[A <: Obj, B <: Obj, C <: Obj](f: F[A, B])(implicit c: Con[C]): F[Tens[C, A], Tens[C, B]]

}
