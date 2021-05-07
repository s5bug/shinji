package tf.bug.shinji

trait Arrow[
  Obj,
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
  F[_ <: Obj, _ <: Obj]
] extends StrongProfunctor[Obj, Con, Hom, Empty, Prod, F] with Cartesian[Obj, Con, F, Empty, Prod] {

  def lift[A <: Obj, B <: Obj](f: Hom[A, B]): F[A, B]

  override def dimap[A <: Obj, B <: Obj, C <: Obj, D <: Obj](f: Hom[C, A], g: Hom[B, D]): F[A, B] => F[C, D] = {
    implicit val aCon = strongProfunctorCategory.homRightConstraint(f)
    implicit val bCon = strongProfunctorCategory.homLeftConstraint(g)
    implicit val cCon = strongProfunctorCategory.homLeftConstraint(f)
    implicit val dCon = strongProfunctorCategory.homRightConstraint(g)
    val af = lift(f)
    val ag = lift(g)
    fab => compose(ag, compose(fab, af))
  }

  def split[A <: Obj, B <: Obj, C <: Obj, D <: Obj](f: F[A, B], g: F[C, D]): F[Prod[A, C], Prod[B, D]] = {
    implicit val aCon = profunctorLeftConstraint(f)
    implicit val bCon = profunctorRightConstraint(f)
    implicit val cCon = profunctorLeftConstraint(g)
    implicit val dCon = profunctorRightConstraint(g)
    andThen(first(f), second(g))
  }

  def merge[A <: Obj, B <: Obj, C <: Obj](f: F[A, B], g: F[A, C]): F[A, Prod[B, C]] = {
    implicit val aCon = profunctorLeftConstraint(f)
    implicit val bCon = profunctorRightConstraint(f)
    implicit val cCon = profunctorRightConstraint(g)
    andThen(duplicate[A], split(f, g))
  }

}

trait ArrowLoop[
  Obj,
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
  F[_ <: Obj, _ <: Obj]
] extends Arrow[Obj, Con, Hom, Empty, Prod, F] {

  def loop[A <: Obj, B <: Obj, C <: Obj](f: F[Prod[A, C], Prod[B, C]]): F[A, B]

}
