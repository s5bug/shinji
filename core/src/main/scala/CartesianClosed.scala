package tf.bug.shinji

trait CartesianClosed[Obj, Hom[_ <: Obj, _ <: Obj], Empty <: Obj, Prod[_ <: Obj, _ <: Obj] <: Obj, Exp[_ <: Obj, _ <: Obj] <: Obj] extends Cartesian[Obj, Hom, Empty, Prod] with ClosedMonoidal[Obj, Hom, Empty, Prod, Exp] with StrongProfunctor[Obj, Hom, Empty, Prod, Exp]
