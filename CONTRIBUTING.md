# Contributing Guidelines

**DD** follow [the seven rules of a great Git commit message][1].

**DD** follow [the Clojure Style Guide][2].

**DD** include tests for your change when appropriate.

**DD** ensure that the CI checks pass.

**DD** squash the commits in your PR to remove corrections
irrelevant to the code history, once the PR has been reviewed.

**DD** feel free to pester the project maintainers about the PR if it
hasn't been responded to. Sometimes notifications can be missed.

**DON'T** overuse vertical whitespace; avoid multiple sequential blank
lines.

**DON'T** include more than one feature or fix in a single PR.

**DON'T** include changes unrelated to the purpose of the PR. This
includes changing the project version number, adding lines to the
`.gitignore` file, or changing the indentation or formatting.

**DON'T** open a new PR if changes are requested. Just push to the
same branch and the PR will be updated.

[1]: https://chris.beams.io/posts/git-commit/#seven-rules
[2]: https://github.com/bbatsov/clojure-style-guide
