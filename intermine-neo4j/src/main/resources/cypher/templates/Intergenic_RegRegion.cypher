MATCH (intergenicregion :IntergenicRegion),
(intergenicregion)-[:PART_OF]-(intergenicregion_organism :Organism),
(intergenicregion)-[:OVERLAPS]-(intergenicregion_overlappingfeatures :SequenceFeature),
(intergenicregion_overlappingfeatures)-[:chromosomeLocation]-(intergenicregion_overlappingfeatures_chromosomelocation :Location),
(intergenicregion_overlappingfeatures)-[:dataSets]-(intergenicregion_overlappingfeatures_datasets :DataSet)

WHERE intergenicregion_organism.name = 'Drosophila melanogaster' AND intergenicregion.primaryIdentifier = 'intergenic_region_chr2L_10295386..10295858'
RETURN intergenicregion.primaryIdentifier,
intergenicregion_overlappingfeatures.primaryIdentifier,
intergenicregion_overlappingfeatures_chromosomelocation.start,
intergenicregion_overlappingfeatures_chromosomelocation.end,
intergenicregion_overlappingfeatures.length,
intergenicregion_overlappingfeatures_datasets.name
ORDER BY intergenicregion.primaryIdentifier ASC