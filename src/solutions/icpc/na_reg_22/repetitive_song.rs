use std::{cmp, collections::HashMap, fmt::Debug, io, str::FromStr};

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
    let mut buffer = String::new();
    let n = next::<usize>(&mut buffer);

    let mut lyrics_map = HashMap::new();

    let mut min_jump = usize::MAX;
    for i in 0..n {
        buffer.clear();
        let lyric = next::<String>(&mut buffer);
        if let Some(&j) = lyrics_map.get(&lyric) {
            min_jump = cmp::min(min_jump, i - j);
        }
        lyrics_map.insert(lyric, i);
    }

    if min_jump == usize::MAX {
        println!("0");
    } else {
        println!("{}", n - min_jump);
    }
}
