package tf.bug.shinji

trait StrongMonoidalFunctor[
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
] extends MonoidalFunctor[ObjC, ConC, HomC, IC, TensC, ObjD, ConD, HomD, ID, TensD, F] {

  def extract: HomD[F[IC], ID]
  def split[X <: ObjC, Y <: ObjC](implicit x: ConC[X], y: ConC[Y]): HomD[F[TensC[X, Y]], TensD[F[X], F[Y]]]

}
