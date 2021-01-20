package tf.bug.shinji

trait Functor[ObjC, HomC[_ <: ObjC, _ <: ObjC], ObjD, HomD[_ <: ObjD, _ <: ObjD], F[_ <: ObjC] <: ObjD] {
  val inCategory: Category[ObjC, HomC]
  val outCategory: Category[ObjD, HomD]
  def map[A <: ObjC, B <: ObjC](f: HomC[A, B]): HomD[F[A], F[B]]
}

trait Endofunctor[Obj, Hom[_ <: Obj, _ <: Obj], F[_ <: Obj] <: Obj] extends Functor[Obj, Hom, Obj, Hom, F] {
  val category: Category[Obj, Hom]
  override final val inCategory = category
  override final val outCategory = category
}
