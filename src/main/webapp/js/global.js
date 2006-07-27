window.onload = function() {
    settings = {
        tl: { radius: 10 },
        tr: { radius: 10 },
        bl: { radius: 10 },
        br: { radius: 10 },
        antiAlias: true,
        autoPad: true,
        validTags: ["div"]
    }

    /*
    Usage:

    newCornersObj = new curvyCorners(settingsObj, classNameStr);
    newCornersObj = new curvyCorners(settingsObj, divObj1[, divObj2[, divObj3[, . . . [, divObjN]]]]);
    */
    new curvyCorners(settings, "curvyCorner").applyCornersToAll();
}
