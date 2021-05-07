package tf.bug.shinji

/**
 * A Functor from C to D.
 *
 * @tparam ObjC C's Obj
 * @tparam HomC C's Hom
 * @tparam ObjD D's Obj
 * @tparam HomD D's Hom
 * @tparam F The Functor's ObjC → ObjD mapping
 */
trait Functor[
  ObjC,
  HomC[_ <: ObjC, _ <: ObjC],
  ObjD,
  HomD[_ <: ObjD, _ <: ObjD],
  F[_ <: ObjC] <: ObjD
] {
  def inFunctorCategory: Category[ObjC, HomC]
  def outFunctorCategory: Category[ObjD, HomD]

  /**
   * The Functor's HomC → HomD mapping.
   *
   * @param f The morphism in C
   * @return The morphism in Im_F
   */
  def map[A <: ObjC, B <: ObjC](f: HomC[A, B]): HomD[F[A], F[B]]
}

trait Endofunctor[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  F[_ <: Obj] <: Obj
] extends Functor[Obj, Hom, Obj, Hom, F] {
  def endofunctorCategory: Category[Obj, Hom]
  override final def inFunctorCategory = endofunctorCategory
  override final def outFunctorCategory = endofunctorCategory
}
