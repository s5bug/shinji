package tf.bug.shinji

trait CartesianClosedCategory[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj,
] extends SymmetricClosedMonoidalCategory[Obj, Val, Hom, I, Prod, Exp] with CartesianCategory[Obj, Val, Hom, I, Prod] {

}
