package tf.bug.shinji

trait Profunctor[ObjC, HomC[_ <: ObjC, _ <: ObjC], ObjD, HomD[_ <: ObjD, _ <: ObjD], ObjE, HomE[_ <: ObjE, _ <: ObjE], F[_ <: ObjC, _ <: ObjD] <: ObjE] {

  val leftCategory: Category[ObjC, HomC]
  val rightCategory: Category[ObjD, HomD]
  val outCategory: Category[ObjE, HomE]

  def dimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](f: HomC[C, A], g: HomD[B, D]): HomE[F[A, B], F[C, D]]

}
