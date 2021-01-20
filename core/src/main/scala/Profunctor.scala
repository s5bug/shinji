package tf.bug.shinji

trait Profunctor[ObjC, HomC[_ <: ObjC, _ <: ObjC], ObjD, HomD[_ <: ObjD, _ <: ObjD], ObjE, HomE[_ <: ObjE, _ <: ObjE], F[_ <: ObjC, _ <: ObjD] <: ObjE] {
  def dimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](f: HomC[C, A], g: HomD[B, D]): HomE[F[A, B], F[C, D]]
}
