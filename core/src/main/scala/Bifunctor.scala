package tf.bug.shinji

trait Bifunctor[
  ObjC,
  ConC[_ <: ObjC],
  HomC[_ <: ObjC, _ <: ObjC],
  ObjD,
  ConD[_ <: ObjD],
  HomD[_ <: ObjD, _ <: ObjD],
  ObjE,
  ConE[_ <: ObjE],
  HomE[_ <: ObjE, _ <: ObjE],
  F[_ <: ObjC, _ <: ObjD] <: ObjE
] {

  def leftBifunctorCategory: Category[ObjC, ConC, HomC]
  def rightBifunctorCategory: Category[ObjD, ConD, HomD]
  def outBifunctorCategory: Category[ObjE, ConE, HomE]

  implicit def bifunctorApplyConstraint[A <: ObjC, B <: ObjD](implicit a: ConC[A], b: ConD[B]): ConE[F[A, B]]
  def bifunctorLeftConstraint[A <: ObjC, B <: ObjD](implicit e: ConE[F[A, B]]): ConC[A]
  def bifunctorRightConstraint[A <: ObjC, B <: ObjD](implicit e: ConE[F[A, B]]): ConD[B]

  def bimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](f: HomC[A, C], g: HomD[B, D]): HomE[F[A, B], F[C, D]]

  def lbimap[A <: ObjC, B <: ObjD, C <: ObjC](f: HomC[A, C])(implicit b: ConD[B]): HomE[F[A, B], F[C, B]] =
    bimap(f, rightBifunctorCategory.id[B])

  def rbimap[A <: ObjC, B <: ObjD, C <: ObjD](f: HomD[B, C])(implicit a: ConC[A]): HomE[F[A, B], F[A, C]] =
    bimap(leftBifunctorCategory.id[A], f)

}
