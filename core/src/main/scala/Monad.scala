package tf.bug.shinji

trait Monad[Obj, Hom[_ <: Obj, _ <: Obj], F[_ <: Obj] <: Obj] extends Functor[Obj, Hom, Obj, Hom, F] {

  def unit[A <: Obj]: Hom[A, F[A]]
  def join[A <: Obj]: Hom[F[F[A]], F[A]]

}
