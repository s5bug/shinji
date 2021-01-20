package tf.bug.shinji

trait Functor[ObjC, HomC[_ <: ObjC, _ <: ObjC], ObjD, HomD[_ <: ObjD, _ <: ObjD], F[_ <: ObjC] <: ObjD] {
  def map[A <: ObjC, B <: ObjC](f: HomC[A, B]): HomD[F[A], F[B]]
}
