use std::{fmt::Debug, io, str::FromStr};

//============================================
// StdIn Helpers
//============================================

#[allow(unused)]
#[inline(always)]
fn next<T: FromStr>(buffer: &mut String) -> T
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    buffer.trim().parse::<T>().unwrap()
}

#[allow(unused)]
#[inline(always)]
fn next_tuple<T: FromStr>(buffer: &mut String) -> (T, T)
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    let mut iter = buffer.trim().split(' ').map(|s| s.parse::<T>().unwrap());
    (iter.next().unwrap(), iter.next().unwrap())
}

#[allow(unused)]
#[inline(always)]
fn next_vec<T: FromStr>(buffer: &mut String) -> Vec<T>
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    buffer
        .trim()
        .split(' ')
        .map(|s| s.parse::<T>().unwrap())
        .collect::<Vec<T>>()
}

//============================================
// Solution
//============================================

pub fn main() {
    let (ds, ys) = next_tuple::<usize>(&mut String::new());
    let (dm, ym) = next_tuple::<usize>(&mut String::new());
    let (ps, pm) = ((ys - ds) % ys, (ym - dm) % ym);
    let mut t = ps;
    while t < 5_000 && (t % ys != ps || t % ym != pm) {
        t += ys;
    }
    println!("{}", t);
}
