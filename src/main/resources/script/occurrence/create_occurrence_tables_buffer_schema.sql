CREATE SCHEMA IF NOT EXISTS buffer;
CREATE TABLE IF NOT EXISTS buffer.occurrence (
auto_id INTEGER NOT NULL,
associatedmedia TEXT,
associatedsequences TEXT,
basisofrecord TEXT,
bibliographiccitation TEXT,
catalogNumber TEXT,
occurrenceid TEXT,
_references TEXT,
collectionCode TEXT,
continent TEXT,
country TEXT,
county TEXT,
municipality TEXT,
datasetName TEXT,
habitat TEXT,
locality TEXT,
kingdom TEXT,
phylum TEXT,
_class TEXT,
_order TEXT,
family TEXT,
genus TEXT,
specificepithet TEXT,
infraspecificepithet TEXT,
species TEXT,
recordedBy TEXT,
recordNumber TEXT,
institutioncode TEXT,
scientificName TEXT,
rawscientificname TEXT,
scientificnameauthorship TEXT,
stateProvince TEXT,
typeStatus TEXT,
taxonRank TEXT,
verbatimElevation TEXT,
minimumelevationinmeters DOUBLE PRECISION,
maximumelevationinmeters DOUBLE PRECISION,
decimallatitude DOUBLE PRECISION,
decimallongitude DOUBLE PRECISION,
eventdate TEXT,
starteventdate DATE,
endeventdate DATE,
sday smallint,
smonth smallint,
syear smallint,
eday smallint,
emonth smallint,
eyear smallint,
decade smallint,
averagealtituderounded integer,
hascoordinates boolean,
hasmedia boolean,
hastypestatus boolean,
hasassociatedsequences boolean,
sourcefileid TEXT,
publishername TEXT,
resourcename TEXT,
dwcaid TEXT,
CONSTRAINT occurrence_pkey PRIMARY KEY (auto_id )
);

CREATE SEQUENCE IF NOT EXISTS buffer.occurrence_raw_auto_id_seq;
CREATE TABLE IF NOT EXISTS buffer.occurrence_raw (
auto_id INTEGER NOT NULL,
dwcaid character varying(255),
sourcefileid character varying(255) NOT NULL,
acceptedNameUsage TEXT,
acceptedNameUsageID TEXT,
accessRights TEXT,
associatedMedia TEXT,
associatedOccurrences TEXT,
associatedReferences TEXT,
associatedSequences TEXT,
associatedTaxa TEXT,
basisOfRecord TEXT,
bed TEXT,
behavior TEXT,
bibliographicCitation TEXT,
catalogNumber TEXT,
_class TEXT,
collectionCode TEXT,
collectionID TEXT,
continent TEXT,
coordinatePrecision TEXT,
coordinateUncertaintyInMeters TEXT,
country TEXT,
countryCode TEXT,
county TEXT,
dataGeneralizations TEXT,
datasetID TEXT,
datasetName TEXT,
dateIdentified TEXT,
day TEXT,
decimalLatitude TEXT,
decimalLongitude TEXT,
disposition TEXT,
dynamicProperties TEXT,
earliestAgeOrLowestStage TEXT,
earliestEonOrLowestEonothem TEXT,
earliestEpochOrLowestSeries TEXT,
earliestEraOrLowestErathem TEXT,
earliestPeriodOrLowestSystem TEXT,
endDayOfYear TEXT,
establishmentMeans TEXT,
eventDate TEXT,
eventID TEXT,
eventRemarks TEXT,
eventTime TEXT,
family TEXT,
fieldNotes TEXT,
fieldNumber TEXT,
footprintSpatialFit TEXT,
footprintSRS TEXT,
footprintWKT TEXT,
formation TEXT,
genus TEXT,
geodeticDatum TEXT,
geologicalContextID TEXT,
georeferencedBy TEXT,
georeferencedDate TEXT,
georeferenceProtocol TEXT,
georeferenceRemarks TEXT,
georeferenceSources TEXT,
georeferenceVerificationStatus TEXT,
_group TEXT,
habitat TEXT,
higherClassification TEXT,
higherGeography TEXT,
higherGeographyID TEXT,
highestBiostratigraphicZone TEXT,
identificationID TEXT,
identificationQualifier TEXT,
identificationReferences TEXT,
identificationRemarks TEXT,
identificationVerificationStatus TEXT,
identifiedBy TEXT,
individualCount TEXT,
individualID TEXT,
informationWithheld TEXT,
infraspecificEpithet TEXT,
institutionCode TEXT,
institutionID TEXT,
island TEXT,
islandGroup TEXT,
kingdom TEXT,
language TEXT,
latestAgeOrHighestStage TEXT,
latestEonOrHighestEonothem TEXT,
latestEpochOrHighestSeries TEXT,
latestEraOrHighestErathem TEXT,
latestPeriodOrHighestSystem TEXT,
lifeStage TEXT,
lithostratigraphicTerms TEXT,
locality TEXT,
locationAccordingTo TEXT,
locationID TEXT,
locationRemarks TEXT,
lowestBiostratigraphicZone TEXT,
maximumDepthInMeters TEXT,
maximumDistanceAboveSurfaceInMeters TEXT,
maximumElevationInMeters TEXT,
member TEXT,
minimumDepthInMeters TEXT,
minimumDistanceAboveSurfaceInMeters TEXT,
minimumElevationInMeters TEXT,
modified TEXT,
month TEXT,
municipality TEXT,
nameAccordingTo TEXT,
nameAccordingToID TEXT,
namePublishedIn TEXT,
namePublishedInID TEXT,
namePublishedInYear TEXT,
nomenclaturalCode TEXT,
nomenclaturalStatus TEXT,
occurrenceID TEXT,
occurrenceRemarks TEXT,
occurrenceStatus TEXT,
_order TEXT,
originalNameUsage TEXT,
originalNameUsageID TEXT,
otherCatalogNumbers TEXT,
ownerInstitutionCode TEXT,
parentNameUsage TEXT,
parentNameUsageID TEXT,
phylum TEXT,
pointRadiusSpatialFit TEXT,
preparations TEXT,
previousIdentifications TEXT,
recordedBy TEXT,
recordNumber TEXT,
_references TEXT,
reproductiveCondition TEXT,
rights TEXT,
rightsHolder TEXT,
samplingEffort TEXT,
samplingProtocol TEXT,
scientificName TEXT,
scientificNameAuthorship TEXT,
scientificNameID TEXT,
sex TEXT,
specificEpithet TEXT,
startDayOfYear TEXT,
stateProvince TEXT,
subgenus TEXT,
taxonConceptID TEXT,
taxonID TEXT,
taxonomicStatus TEXT,
taxonRank TEXT,
taxonRemarks TEXT,
type TEXT,
typeStatus TEXT,
verbatimCoordinates TEXT,
verbatimCoordinateSystem TEXT,
verbatimDepth TEXT,
verbatimElevation TEXT,
verbatimEventDate TEXT,
verbatimLatitude TEXT,
verbatimLocality TEXT,
verbatimLongitude TEXT,
verbatimSRS TEXT,
verbatimTaxonRank TEXT,
vernacularName TEXT,
waterBody TEXT,
year TEXT,
CONSTRAINT occurrence_raw_pkey PRIMARY KEY (auto_id ),
CONSTRAINT occurrence_raw_dwcaid_sourcefileid_key UNIQUE (dwcaid , sourcefileid)
);

CREATE SEQUENCE IF NOT EXISTS buffer.unique_values_id_seq;
CREATE TABLE IF NOT EXISTS buffer.unique_values
(
  id integer DEFAULT nextval('buffer.unique_values_id_seq') NOT NULL,
  key TEXT NOT NULL,
  occurrence_count integer NOT NULL,
  value TEXT NOT NULL,
  unaccented_value TEXT,
  CONSTRAINT unique_values_pkey PRIMARY KEY (id )
);

CREATE SEQUENCE IF NOT EXISTS buffer.download_log_id_seq;
CREATE TABLE IF NOT EXISTS buffer.download_log
(
  id integer DEFAULT nextval('buffer.download_log_id_seq') NOT NULL,
  event_date timestamp,
  search_criteria TEXT,
  number_of_records integer,
  email TEXT,
  CONSTRAINT download_log_pkey PRIMARY KEY (id )
);


/* Structure to persist resource metadata information: */
CREATE SEQUENCE IF NOT EXISTS buffer.resource_information_id_seq;
CREATE TABLE IF NOT EXISTS buffer.resource_information
(
	auto_id integer DEFAULT nextval('buffer.resource_information_id_seq') NOT NULL,
	resource_uuid TEXT,
	resource_name TEXT,
	alternate_identifier TEXT,
	title TEXT,
	publication_date date,
	language TEXT,
	_abstract TEXT,
	keyword TEXT,
	keyword_thesaurus TEXT,
	intellectual_rights TEXT,
	citation TEXT,
	hierarchy_level TEXT,
	resource_logo_url TEXT,
	parent_collection_identifier TEXT,
	collection_identifier TEXT,
	collection_name TEXT,
	CONSTRAINT resource_information_pkey PRIMARY KEY (auto_id)
);

/* Structure to persist publisher information: */
CREATE SEQUENCE IF NOT EXISTS buffer.publisher_information_id_seq;
CREATE TABLE IF NOT EXISTS buffer.publisher_information
(
	auto_id integer DEFAULT nextval('buffer.publisher_information_id_seq') NOT NULL,
	name TEXT,
	description TEXT,
	address TEXT,
	city TEXT,
	administrative_area TEXT,
	postal_code TEXT,
	homepage TEXT,
	email TEXT,
	phone TEXT,
	logo_url TEXT,
	decimallatitude DOUBLE PRECISION,
	decimallongitude DOUBLE PRECISION,
	record_count integer,
	CONSTRAINT publisher_information_pkey PRIMARY KEY (auto_id)
);

/* Structure to persist contacts information: */
CREATE SEQUENCE IF NOT EXISTS buffer.contact_id_seq;
CREATE TABLE IF NOT EXISTS buffer.contact
(
	auto_id integer DEFAULT nextval('buffer.contact_id_seq') NOT NULL,
	name TEXT,
	position_name TEXT,
	organization_name TEXT,
	address TEXT,
	city TEXT,
	administrative_area TEXT,
	country TEXT,
	postal_code TEXT,
	phone TEXT,
	email TEXT,
	role TEXT,
	publisher_information_fkey integer references publisher_information(auto_id),
	resource_information_fkey integer references resource_information(auto_id),
	CONSTRAINT contact_pkey PRIMARY KEY (auto_id)
);

CREATE SEQUENCE IF NOT EXISTS buffer.occurrence_extension_id_seq;
CREATE TABLE IF NOT EXISTS buffer.occurrence_extension
(
	auto_id bigint NOT NULL,
	dwcaid TEXT,
	sourcefileid TEXT,
	resource_uuid TEXT,
	ext_type TEXT, 
	ext_version TEXT, 
	ext_data hstore
);
