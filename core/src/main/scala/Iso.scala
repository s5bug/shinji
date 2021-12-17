package tf.bug.shinji

case class Iso[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  A <: Obj,
  B <: Obj,
](
  forward: Hom[A, B],
  backward: Hom[B, A],
)

object Iso {

  def compose[
    Obj,
    Val[_ <: Obj],
    Hom[_ <: Obj, _ <: Obj],
    A <: Obj,
    B <: Obj,
    C <: Obj,
  ](
    cat: Category[Obj, Val, Hom],
    outer: Iso[Obj, Val, Hom, A, B],
    inner: Iso[Obj, Val, Hom, B, C],
  ): Iso[Obj, Val, Hom, A, C] =
    Iso(
      cat.compose(outer.forward, inner.forward),
      cat.compose(inner.backward, outer.backward),
    )

}
