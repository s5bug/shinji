package tf.bug.shinji

trait MonoidalFunctor[
  ObjC,
  ConC[_ <: ObjC],
  HomC[_ <: ObjC, _ <: ObjC],
  IC <: ObjC,
  TensC[_ <: ObjC, _ <: ObjC] <: ObjC,
  ObjD,
  ConD[_ <: ObjD],
  HomD[_ <: ObjD, _ <: ObjD],
  ID <: ObjD,
  TensD[_ <: ObjD, _ <: ObjD] <: ObjD,
  F[_ <: ObjC] <: ObjD
] extends Functor[ObjC, ConC, HomC, ObjD, ConD, HomD, F] {

  val inMonoidalFunctorCategory: Monoidal[ObjC, ConC, HomC, IC, TensC]
  override final val inFunctorCategory: Category[ObjC, ConC, HomC] = inMonoidalFunctorCategory
  val outMonoidalFunctorCategory: Monoidal[ObjD, ConD, HomD, ID, TensD]
  override final val outFunctorCategory: Category[ObjD, ConD, HomD] = outMonoidalFunctorCategory

  def pure: HomD[ID, F[IC]]
  def join[X <: ObjC, Y <: ObjC](implicit x: ConC[X], y: ConC[Y]): HomD[TensD[F[X], F[Y]], F[TensC[X, Y]]]

}
