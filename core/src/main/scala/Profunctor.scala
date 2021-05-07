package tf.bug.shinji

trait Profunctor[
  ObjC,
  ConC[_ <: ObjC],
  HomC[_ <: ObjC, _ <: ObjC],
  ObjD,
  ConD[_ <: ObjD],
  HomD[_ <: ObjD, _ <: ObjD],
  F[_ <: ObjC, _ <: ObjD]
] {

  def profunctorLeftConstraint[A <: ObjC, B <: ObjD](f: F[A, B]): ConC[A]
  def profunctorRightConstraint[A <: ObjC, B <: ObjD](f: F[A, B]): ConD[B]

  def leftProfunctorCategory: Category[ObjC, ConC, HomC]
  def rightProfunctorCategory: Category[ObjD, ConD, HomD]

  def dimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](f: HomC[C, A], g: HomD[B, D]): F[A, B] => F[C, D]

  def ldimap[A <: ObjC, B <: ObjD, C <: ObjC](f: HomC[C, A])(implicit b: ConD[B]): F[A, B] => F[C, B] = dimap(f, rightProfunctorCategory.id[B])
  def rdimap[A <: ObjC, B <: ObjD, C <: ObjD](f: HomD[B, C])(implicit a: ConC[A]): F[A, B] => F[A, C] = dimap(leftProfunctorCategory.id[A], f)

}
