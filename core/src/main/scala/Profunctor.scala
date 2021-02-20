package tf.bug.shinji

trait Profunctor[
  ObjC,
  HomC[_ <: ObjC, _ <: ObjC],
  ObjD,
  HomD[_ <: ObjD, _ <: ObjD],
  F[_ <: ObjC, _ <: ObjD]
] {

  val leftProfunctorCategory: Category[ObjC, HomC]
  val rightProfunctorCategory: Category[ObjD, HomD]

  def dimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](f: HomC[C, A], g: HomD[B, D]): F[A, B] => F[C, D]

  def ldimap[A <: ObjC, B <: ObjD, C <: ObjC](f: HomC[C, A]): F[A, B] => F[C, B] = dimap(f, rightProfunctorCategory.id[B])
  def rdimap[A <: ObjC, B <: ObjD, C <: ObjD](f: HomD[B, C]): F[A, B] => F[A, C] = dimap(leftProfunctorCategory.id[A], f)

}
