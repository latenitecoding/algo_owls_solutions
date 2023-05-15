use clap::Parser;

mod solutions;

#[derive(Debug, Parser)]
#[command(author, version, about, long_about=None)]
struct Args {
    #[arg(short, long)]
    solution: String,
}

fn main() {
    let args = Args::parse();
    solutions::call_solution(&args.solution);
}
