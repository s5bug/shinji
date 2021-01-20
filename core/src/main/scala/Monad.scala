package tf.bug.shinji

trait Monad[Obj, Hom[_ <: Obj, _ <: Obj], F[_ <: Obj] <: Obj] extends Functor[Obj, Hom, Obj, Hom, F] {

  def unit[A <: Obj]: Hom[A, F[A]]
  def join[A <: Obj]: Hom[F[F[A]], F[A]]

  def flatMap[A <: Obj, B <: Obj](f: Hom[A, F[B]])(implicit comp: Compose[Obj, Hom]): Hom[F[A], F[B]] =
    comp.andThen(map(f), join[B])

}
