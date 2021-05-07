package tf.bug.shinji

trait Arrow[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
  F[_ <: Obj, _ <: Obj]
] extends StrongProfunctor[Obj, Hom, Empty, Prod, F] with Cartesian[Obj, F, Empty, Prod] {

  def lift[A <: Obj, B <: Obj](f: Hom[A, B]): F[A, B]

  override def dimap[A <: Obj, B <: Obj, C <: Obj, D <: Obj](f: Hom[C, A], g: Hom[B, D]): F[A, B] => F[C, D] = {
    val af = lift(f)
    val ag = lift(g)
    fab => compose(ag, compose(fab, af))
  }

  def split[A <: Obj, B <: Obj, C <: Obj, D <: Obj](f: F[A, B], g: F[C, D]): F[Prod[A, C], Prod[B, D]] =
    andThen(first(f), second(g))

  def merge[A <: Obj, B <: Obj, C <: Obj](f: F[A, B], g: F[A, C]): F[A, Prod[B, C]] =
    andThen(duplicate[A], split(f, g))

}

trait ArrowLoop[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
  F[_ <: Obj, _ <: Obj]
] extends Arrow[Obj, Hom, Empty, Prod, F] {

  def loop[A <: Obj, B <: Obj, C <: Obj](f: F[Prod[A, C], Prod[B, C]]): F[A, B]

}
