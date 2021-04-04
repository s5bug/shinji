package tf.bug.shinji

trait StrongMonoidalFunctor[
  ObjC,
  HomC[_ <: ObjC, _ <: ObjC],
  IC <: ObjC,
  TensC[_ <: ObjC, _ <: ObjC] <: ObjC,
  ObjD,
  HomD[_ <: ObjD, _ <: ObjD],
  ID <: ObjD,
  TensD[_ <: ObjD, _ <: ObjD] <: ObjD,
  F[_ <: ObjC] <: ObjD
] extends MonoidalFunctor[ObjC, HomC, IC, TensC, ObjD, HomD, ID, TensD, F] {

  def extract: HomD[F[IC], ID]
  def split[X <: ObjC, Y <: ObjC]: HomD[F[TensC[X, Y]], TensD[F[X], F[Y]]]

}
