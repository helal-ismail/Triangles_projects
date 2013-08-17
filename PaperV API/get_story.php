<?php
require('config.php');
	$iStoryId = $_REQUEST['story_id'];
	$aStory = phpfox::getService('story')->getById($iStoryId);
	phpfox::getService('story')->getIphoneStoryExtra($aStory);
	$result[]=array('success'=>true,'data'=>$aStory);
	echo json_encode($result);
?>