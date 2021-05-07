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
  ConC[_ <: ObjC],
  HomC[_ <: ObjC, _ <: ObjC],
  ObjD,
  ConD[_ <: ObjD],
  HomD[_ <: ObjD, _ <: ObjD],
  F[_ <: ObjC] <: ObjD
] {
  def inFunctorCategory: Category[ObjC, ConC, HomC]
  def outFunctorCategory: Category[ObjD, ConD, HomD]

  implicit def functorApplyConstraint[A <: ObjC](implicit a: ConC[A]): ConD[F[A]]
  def functorExtractConstraint[A <: ObjC](implicit fa: ConD[F[A]]): ConC[A]

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
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  F[_ <: Obj] <: Obj
] extends Functor[Obj, Con, Hom, Obj, Con, Hom, F] {

  def endofunctorCategory: Category[Obj, Con, Hom]
  override final def inFunctorCategory: Category[Obj, Con, Hom] = endofunctorCategory
  override final def outFunctorCategory: Category[Obj, Con, Hom] = endofunctorCategory
}
