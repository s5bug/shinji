package tf.bug.shinji

trait Monad[
  Obj,
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  F[_ <: Obj] <: Obj
] extends Endofunctor[Obj, Con, Hom, F] {

  def pure[A <: Obj](implicit a: Con[A]): Hom[A, F[A]]
  def flatten[A <: Obj](implicit a: Con[A]): Hom[F[F[A]], F[A]]

  def flatMap[A <: Obj, B <: Obj](f: Hom[A, F[B]]): Hom[F[A], F[B]] = {
    implicit val aConstraint = endofunctorCategory.homLeftConstraint(f)
    implicit val faConstraint = functorApplyConstraint(aConstraint)
    implicit val fbConstraint = endofunctorCategory.homRightConstraint(f)
    implicit val bConstraint = functorExtractConstraint(fbConstraint)
    implicit val ffbConstraint = functorApplyConstraint(fbConstraint)
    endofunctorCategory.andThen(map(f), flatten[B])
  }

}
