package tf.bug.shinji
package syntax

trait MonoidalSyntax {

  def bimapL[
    Obj,
    Hom[_ <: Obj, _ <: Obj],
    I <: Obj,
    Tens[_ <: Obj, _ <: Obj] <: Obj,
    A <: Obj,
    B <: Obj,
    C <: Obj
  ](f: Hom[A, B])(implicit monoidal: Monoidal[Obj, Hom, I, Tens]): Hom[Tens[A, C], Tens[B, C]] =
    monoidal.lbimap(f)

  def bimapR[
    Obj,
    Hom[_ <: Obj, _ <: Obj],
    I <: Obj,
    Tens[_ <: Obj, _ <: Obj] <: Obj,
    A <: Obj,
    B <: Obj,
    C <: Obj
  ](f: Hom[A, B])(implicit monoidal: Monoidal[Obj, Hom, I, Tens]): Hom[Tens[C, A], Tens[C, B]] =
    monoidal.rbimap(f)

}
