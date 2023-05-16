mod icpc {
    pub mod se_reg_22 {
        pub mod blueberry_waffle;
        pub mod fading_wind;
    }
}

pub fn call_solution(solution: &str) {
    match solution {
        "blueberry_waffle" => icpc::se_reg_22::blueberry_waffle::main(),
        "fading_wind" => icpc::se_reg_22::fading_wind::main(),
        _ => println!("No solution for {}", solution),
    }
}
