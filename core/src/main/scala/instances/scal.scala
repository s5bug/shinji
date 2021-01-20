package tf.bug.shinji
package instances

trait scal extends CartesianClosed[Any, Function1, Unit, Tuple2, Function1] {

  override def curry[A, B, C](f: ((A, B)) => C): A => B => C =
    (a: A) => (b: B) => f((a, b))

  override def uncurry[A, B, C](f: A => B => C): ((A, B)) => C =
  { case (a, b) => f(a)(b) }

  override def expId[A]: Unit => A => A =
    (_: Unit) => identity[A]

  override def second[A, B, C]: (B => C) => (A => B) => A => C =
    f => g => compose[A, B, C](f, g)

  override def duplicate[A]: A => (A, A) =
    a => (a, a)

  override def terminate[A]: A => Unit =
    a => ()

  override def associateRight[A, B, C]: (((A, B), C)) => (A, (B, C)) =
    { case ((a, b), c) => (a, (b, c)) }

  override def associateLeft[A, B, C]: ((A, (B, C))) => ((A, B), C) =
    { case (a, (b, c)) => ((a, b), c) }

  override def unitorLeft[A]: ((Unit, A)) => A =
    { case ((), a) => a }

  override def deunitorLeft[A]: A => (Unit, A) =
    a => ((), a)

  override def unitorRight[A]: ((A, Unit)) => A =
    { case (a, ()) => a }

  override def deunitorRight[A]: A => (A, Unit) =
    a => (a, ())

  override def id[A]: A => A = identity[A]

  override def dimap[A, B, C, D](fab: A => B, f: C => A, g: B => D): C => D =
    f.andThen(fab).andThen(g)

  override def compose[A, B, C](f: B => C, g: A => B): A => C =
    f.compose(g)

  override def andThen[A, B, C](f: A => B, g: B => C): A => C =
    f.andThen(g)
  
}
