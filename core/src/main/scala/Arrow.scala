package tf.bug.shinji

trait Arrow[Obj, Hom[_ <: Obj, _ <: Obj], I <: Obj, Tens[_ <: Obj, _ <: Obj] <: Obj, F[_ <: Obj, _ <: Obj]] extends StrongProfunctor[Obj, Hom, I, Tens, F] with Monoidal[Obj, F, I, Tens] {

  def lift[A <: Obj, B <: Obj](f: Hom[A, B]): F[A, B]

  override def dimap[A <: Obj, B <: Obj, C <: Obj, D <: Obj](f: Hom[C, A], g: Hom[B, D]): F[A, B] => F[C, D] = {
    val af = lift(f)
    val ag = lift(g)
    fab => compose(ag, compose(fab, af))
  }

}
