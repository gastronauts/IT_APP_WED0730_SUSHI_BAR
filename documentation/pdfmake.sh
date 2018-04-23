#!/bin/bash

###########################################################
# Verify tools
###########################################################

if [ -z $"(whereis pandoc)" ]; then
    echo "pandoc not found. Please install it."
    exit 1
fi

##########################################################
# Configure target
##########################################################

if [ -z "${OUTLINE_CONFIGURATION}" ]; then
    echo "To run this script OUTLINE_CONFIGURATION var must be set."
    exit 1
fi

CHAPTERS=$(python3 get_chapters.py read "${OUTLINE_CONFIGURATION}")

echo ${CHAPTERS}

if [ -z "${CHAPTERS}" ]; then
    echo "Nothing read from configuration: ${OUTLINE_CONFIGURATION}"
    exit 1
fi

if [ -z "${OUTPUT_FILE}" ]; then
	OUTPUT_FILE="output.pdf"
fi

echo "Document will be saved as ${OUTPUT_FILE}."

###########################################################
# Generate pdf
###########################################################
pandoc -s --toc -N -f markdown_github+yaml_metadata_block  -o ${OUTPUT_FILE} -i meta.yaml ${CHAPTERS}
echo "done."
