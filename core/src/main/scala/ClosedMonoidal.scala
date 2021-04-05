package tf.bug.shinji

/**
 * A Closed Monoidal Category.
 *
 * @tparam Obj The upper bound type.
 * @tparam Hom The homset functor.
 * @tparam I The unit object.
 * @tparam Tens The tensor product functor.
 * @tparam Exp The exponential functor.
 */
trait ClosedMonoidal[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj
] extends Closed[Obj, Hom, I, Exp] with Monoidal[Obj, Hom, I, Tens] {

  /**
   * The forward map in the hom-tens adjunction.
   *
   * @param f A morphism like (A × B) → C
   * @return A morphism like A → (B → C)
   */
  def curry[A <: Obj, B <: Obj, C <: Obj](f: Hom[Tens[A, B], C]): Hom[A, Exp[B, C]]

  /**
   * The backwards map in the hom-tens adjunction.
   *
   * @param f A morphism like A → (B → C)
   * @return A morphism like (A × B) → C
   */
  def uncurry[A <: Obj, B <: Obj, C <: Obj](f: Hom[A, Exp[B, C]]): Hom[Tens[A, B], C]
}
