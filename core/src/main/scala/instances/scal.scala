package tf.bug.shinji
package instances

object Scal extends CartesianClosed[
  /* Obj = */ Any,
  /* Hom = */ Function1,
  /* Empty = */ Unit,
  /* Prod = */ Tuple2,
  /* Exp = */ Function1
] with Arrow[
  /* Obj = */ Any,
  /* Hom = */ Function1,
  /* Empty = */ Unit,
  /* Prod = */ Tuple2,
  /* F = */ Function1
] {

  override def curry[A, B, C](f: ((A, B)) => C): A => B => C =
    a => b => f((a, b))

  override def uncurry[A, B, C](f: A => B => C): ((A, B)) => C =
    { case (a, b) => f(a)(b) }

  override def swap[A <: Any, B <: Any]: ((A, B)) => (B, A) =
    { case (a, b) => (b, a) }

  override def duplicate[A]: A => (A, A) =
    a => (a, a)

  override def terminate[A]: A => Unit =
    _ => ()

  override def bimap[A, B, C, D](f: A => C, g: B => D): ((A, B)) => (C, D) =
    { case (a, b) => (f(a), g(b)) }

  override def expId[A]: Unit => A => A =
    { case () => identity[A] }

  override def expPoint[A]: A => Unit => A =
    a => { case () => a }

  override def expWhole[A <: Any]: (Unit => A) => A =
    p => p(())

  override def expCompose[A, B, C]: (A => B) => (C => A) => C => B =
    f => g => c => f(g(c))

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

  override def id[A]: A => A =
    identity[A]

  override def compose[A, B, C](f: B => C, g: A => B): A => C =
    f.compose(g)

  override def andThen[A, B, C](f: A => B, g: B => C): A => C =
    f.andThen(g)

  override def eval[A, B]: ((A => B, A)) => B =
    { case (f, a) => f(a) }

  override def multiply[A, B]: A => B => (A, B) =
    a => b => (a, b)

  override def lift[A, B](f: A => B): A => B = f

  override def first[A, B, C]: (A => B) => ((A, C)) => (B, C) =
    lbimap

  override def second[A, B, C]: (A => B) => ((C, A)) => (C, B) =
    rbimap

  override def strongProfunctorCategory: Monoidal[Any, Function, Unit, Tuple2] = this

}

object OptionMonad extends Monad[
  /* Obj = */ Any,
  /* Hom = */ Function1,
  /* F = */ Option
] {

  override def endofunctorCategory: Category[Any, Function] = Scal

  override def map[A, B](f: A => B): Option[A] => Option[B] = _.map(f)

  override def pure[A]: A => Option[A] = Option(_)
  override def flatten[A]: Option[Option[A]] => Option[A] = _.flatten

}

object ScalInstances {
  trait all {
    implicit val scalCategory: CartesianClosed[Any, Function1, Unit, Tuple2, Function1] = Scal
    implicit val scalArrow: Arrow[Any, Function1, Unit, Tuple2, Function1] = Scal
    implicit val optionMonad: Monad[Any, Function1, Option] = OptionMonad
  }
}
