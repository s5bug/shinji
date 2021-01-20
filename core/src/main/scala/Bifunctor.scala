package tf.bug.shinji

trait Bifunctor[ObjC, HomC[_ <: ObjC, _ <: ObjC], ObjD, HomD[_ <: ObjD, _ <: ObjD], ObjE, HomE[_ <: ObjE, _ <: ObjE], F[_ <: ObjC, _ <: ObjD] <: ObjE] {

  def bimap[A <: ObjC, B <: ObjD, C <: ObjC, D <: ObjD](f: HomC[A, C], g: HomD[B, D]): HomE[F[A, B], F[C, D]]

  def lmap[A <: ObjC, B <: ObjD, C <: ObjC](f: HomC[A, C])(implicit cat: Category[ObjD, HomD]): HomE[F[A, B], F[C, B]] =
    bimap(f, cat.id[B])

  def rmap[A <: ObjC, B <: ObjD, C <: ObjD](f: HomD[B, C])(implicit cat: Category[ObjC, HomC]): HomE[F[A, B], F[A, C]] =
    bimap(cat.id[A], f)

}
