package tf.bug.shinji

trait StrongProfunctor[Obj, Hom[_ <: Obj, _ <: Obj], I <: Obj, Tens[_ <: Obj, _ <: Obj] <: Obj, F[_ <: Obj, _ <: Obj] <: Obj] extends Profunctor[Obj, Hom, Obj, Hom, Obj, Hom, F] {

  def first[A <: Obj, B <: Obj, C <: Obj]: Hom[F[A, B], F[Tens[A, C], Tens[B, C]]]
  def second[A <: Obj, B <: Obj, C <: Obj]: Hom[F[A, B], F[Tens[C, A], Tens[C, B]]]

}
