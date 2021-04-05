package tf.bug.shinji

/**
 * A Closed Category.
 *
 * @tparam Obj The upper bound type.
 * @tparam Hom The homset functor.
 * @tparam I The unit object.
 * @tparam Exp The internal hom functor.
 */
trait Closed[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj
] extends Category[Obj, Hom] {
  /**
   * A point to the identity internal hom.
   *
   * @tparam A The target object.
   * @return A point to an internal hom `id` such that `id ∘ f ≅ f ∘ id ≅ f`.
   */
  def expId[A <: Obj]: Hom[I, Exp[A, A]]

  /**
   * The forward map in the isomorphism between the identity functor and Exp[I, *].
   *
   * @tparam A The target object.
   * @return A morphism that creates an internal point.
   */
  def expPoint[A <: Obj]: Hom[A, Exp[I, A]]

  /**
   * The backwards map in the isomorphism between the identity functor and Exp[I, *].
   *
   * @tparam A The target object.
   * @return A morphism that extracts from an internal point.
   */
  def expWhole[A <: Obj]: Hom[Exp[I, A], A]

  /**
   * The composition operation for the internal hom.
   */
  def expCompose[A <: Obj, B <: Obj, C <: Obj]: Hom[Exp[A, B], Exp[Exp[C, A], Exp[C, B]]]
}
