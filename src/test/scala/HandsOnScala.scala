import part1_1._
import part1_2._
import part2._

import org.scalatest._
import support.CustomStopper
import recorder.ReportToTheStopper

class HandsOn extends Spec {
  override def run(testName: Option[String], args: Args) = {
    if(!CustomStopper.oneTestFailed)
      super.run(testName, args.copy(reporter = new ReportToTheStopper(args.reporter), stopper = CustomStopper))
    else
      SucceededStatus
  }
}

class HandsOnScala extends HandsOn {
  override def nestedSuites = Vector(
    new part1_1,
    new part1_2,
    new part2
  )
}

class part1_1 extends HandsOn {
  override def nestedSuites = Vector(
    new e0_vars_vals,
    new e1_classes,
    new e2_case_classes,
    new e3_for_loops
  )
}

class part1_2 extends HandsOn {
  override def nestedSuites = Vector(
    new e4_lists,
    new e5_maps,
    new e6_sets,
    new e7_option,
    new e8_higher_order_functions,
    new e9_extractors_and_pattern_matching
  )
}

class part2 extends HandsOn {
  override def nestedSuites = Vector(
    new e2_fp,
    new e0_list,
    new e1_bonus_stream,
    new e3_future
  )
}

class sparkintro extends HandsOn {
  override def nestedSuites = Vector(
    new sparkintro.Intro
  )
}

class scaldingintro extends HandsOn {
  override def nestedSuites = Vector(
    new scaldingintro.Intro
  )
}
