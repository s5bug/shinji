package tf.bug.shinji

trait Bifunctor[ObjC, HomC[_ <: ObjC, _ <: ObjC], ObjD, HomD[_ <: ObjD, _ <: ObjD], ObjE, HomE[_ <: ObjE, _ <: ObjE], F[_ <: ObjC, _ <: ObjD] <: ObjE] {

  val leftBifunctorCategory: Category[ObjC, HomC]
  val rightBifunctorCategory: Category[ObjD, HomD]
  val outBifunctorCategory: Category[ObjE, HomE]

  def bimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](f: HomC[A, C], g: HomD[B, D]): HomE[F[A, B], F[C, D]]

  def lmap[A <: ObjC, B <: ObjD, C <: ObjC](f: HomC[A, C]): HomE[F[A, B], F[C, B]] =
    bimap(f, rightBifunctorCategory.id[B])

  def rmap[A <: ObjC, B <: ObjD, C <: ObjD](f: HomD[B, C]): HomE[F[A, B], F[A, C]] =
    bimap(leftBifunctorCategory.id[A], f)

}
