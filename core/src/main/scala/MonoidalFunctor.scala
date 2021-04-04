package tf.bug.shinji

trait MonoidalFunctor[
  ObjC,
  HomC[_ <: ObjC, _ <: ObjC],
  IC <: ObjC,
  TensC[_ <: ObjC, _ <: ObjC] <: ObjC,
  ObjD,
  HomD[_ <: ObjD, _ <: ObjD],
  ID <: ObjD,
  TensD[_ <: ObjD, _ <: ObjD] <: ObjD,
  F[_ <: ObjC] <: ObjD
] extends Functor[ObjC, HomC, ObjD, HomD, F] {

  val inMonoidalFunctorCategory: Monoidal[ObjC, HomC, IC, TensC]
  override final val inFunctorCategory: Category[ObjC, HomC] = inMonoidalFunctorCategory
  val outMonoidalFunctorCategory: Monoidal[ObjD, HomD, ID, TensD]
  override final val outFunctorCategory: Category[ObjD, HomD] = outMonoidalFunctorCategory

  def pure: HomD[ID, F[IC]]
  def join[X <: ObjC, Y <: ObjC]: HomD[TensD[F[X], F[Y]], F[TensC[X, Y]]]

}
