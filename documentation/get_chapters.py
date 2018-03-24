#!/usr/bin/env python3

import yaml
import argparse

OUTLINES_FILE = "outlines.yaml"


def make_handler(chapters):
    for chapter in chapters:
        with open(chapter['src'], 'w') as f:
            f.write('# ' + chapter['name'] + '\n')

            for sub_chapter in chapter.get('sub', []):
                f.write('\n' + '## ' + sub_chapter + '\n')


def read_handler(chapters):
    print(' '.join([chapter['src'] for chapter in chapters]))


def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("mode", choices=['make', 'read'])
    parser.add_argument("configuration")
    args = parser.parse_args()

    return {"mode": args.mode, "configuration": args.configuration}


def get_chapters(mode, configuration):
    data = None

    handler = make_handler if mode == "make" else read_handler

    with open(OUTLINES_FILE, 'r') as stream:
        try:
            data = yaml.load(stream)
        except yaml.YAMLError as err:
            raise RuntimeError(str(err))

    if configuration not in data:
        raise RuntimeError("Could not find configuration: " + configuration)

    handler(data[configuration])


if __name__ == '__main__':
    get_chapters(**parse_args())
