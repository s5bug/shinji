package tf.bug.shinji
package syntax

trait MonoidalSyntax {

  def bimapL[
    Obj,
    Con[_ <: Obj],
    Hom[_ <: Obj, _ <: Obj],
    I <: Obj,
    Tens[_ <: Obj, _ <: Obj] <: Obj,
    A <: Obj,
    B <: Obj,
    C <: Obj
  ](f: Hom[A, B])(implicit monoidal: Monoidal[Obj, Con, Hom, I, Tens], c: Con[C]): Hom[Tens[A, C], Tens[B, C]] =
    monoidal.lbimap(f)

  def bimapR[
    Obj,
    Con[_ <: Obj],
    Hom[_ <: Obj, _ <: Obj],
    I <: Obj,
    Tens[_ <: Obj, _ <: Obj] <: Obj,
    A <: Obj,
    B <: Obj,
    C <: Obj
  ](f: Hom[A, B])(implicit monoidal: Monoidal[Obj, Con, Hom, I, Tens], c: Con[C]): Hom[Tens[C, A], Tens[C, B]] =
    monoidal.rbimap(f)

}
